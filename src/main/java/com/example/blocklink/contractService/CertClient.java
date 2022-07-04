package com.example.blocklink.contractService;

import com.example.blocklink.contract.Cert;
import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple3;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.model.CryptoType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

public class CertClient {
    private BcosSDK bcosSDK;
    private Client client;
    private CryptoKeyPair cryptoKeyPair;

    public String pemAccountFilePath="/home/csj/BlockLinkExam/blocklink/account/";


    //权限分配：预先设定机构账号，给予其部署合约的权限，同时在区块链上的机构节点也能根据实际需求再进行权限分配
    //表权限处理：本来想着使用表来存储数据，后面发现用简单的数据结构就能解决存储问题，所以没有使用表，以后拓展也能进行更进一步的权限控制


    //第二次登录就是证明已经存在账号了，所以直接读取文件就能登录
   public void loadSecondTime(String name){
       @SuppressWarnings("resource")
       ApplicationContext context =
               new ClassPathXmlApplicationContext("file:/home/csj/BlockLinkExam/blocklink/src/test/resources/applicationContext.xml");
       bcosSDK = context.getBean(BcosSDK.class);
       client = bcosSDK.getClient(1);

       CryptoSuite cryptoSuite = client.getCryptoSuite();
       cryptoSuite.loadAccount("pem", pemAccountFilePath+name, null);
       CryptoKeyPair cryptoKeyPair =cryptoSuite.getCryptoKeyPair();
//       System.out.println(cryptoKeyPair1.getAddress());
//       System.out.println(cryptoKeyPair1.toString());
       client.getCryptoSuite().setCryptoKeyPair(cryptoKeyPair);
   }

    //第一次登录，生成账号并且保存账号文件
    public void loadFirstTime(String name) throws Exception {
        @SuppressWarnings("resource")
        ApplicationContext context =
                new ClassPathXmlApplicationContext("file:/home/csj/BlockLinkExam/blocklink/src/test/resources/applicationContext.xml");
        bcosSDK = context.getBean(BcosSDK.class);
        client = bcosSDK.getClient(1);
        //生成新的
        //保存,示例 /home/yy/fisco/asset-app/account/user1 这里就会产生 user1 user1.pub 文件可以用于以后登录

        CryptoSuite cryptoSuite = new CryptoSuite(CryptoType.ECDSA_TYPE);
        CryptoKeyPair cryptoKeyPair = cryptoSuite.createKeyPair();
        saveAccountWithPem(cryptoKeyPair,pemAccountFilePath+name);
        client.getCryptoSuite().setCryptoKeyPair(cryptoKeyPair);
    }

    public void saveAccountWithPem(CryptoKeyPair cryptoKeyPair, String pemFilePath)
    {
        // 以pem的格式保存账户文件到pemFilePath路径
        cryptoKeyPair.storeKeyPairWithPem(pemFilePath);
        System.out.println("success");
    }


    public void deployAssetAndRecordAddr() {
        try {
            Cert cert = Cert.deploy(client, cryptoKeyPair);
            System.out.println(
                    " deploy Asset success, contract address is " + cert.getContractAddress());

            recordAssetAddr(cert.getContractAddress());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            System.out.println(" deploy Cert contract failed, error message is  " + e.getMessage());
        }
    }

    public void recordAssetAddr(String address) throws FileNotFoundException, IOException {
        Properties prop = new Properties();
        prop.setProperty("address", address);
        final Resource contractResource = new ClassPathResource("contract.properties");
        FileOutputStream fileOutputStream = new FileOutputStream(contractResource.getFile());
        prop.store(fileOutputStream, "contract address");
    }

    public String loadAssetAddr() throws Exception {
        // load Asset contact address from contract.properties
        Properties prop = new Properties();
        final Resource contractResource = new ClassPathResource("contract.properties");
        prop.load(contractResource.getInputStream());

        String contractAddress = prop.getProperty("address");
        if (contractAddress == null || contractAddress.trim().equals("")) {
            throw new Exception(" load Asset contract address failed, please deploy it first. ");
        }
        //logger.info(" load Asset address from contract.properties, address is {}", contractAddress);
        return contractAddress;
    }

    public void sendCertificate(String _uid,String context,String ttl) {
        try {
            String contractAddress = loadAssetAddr();
            Cert cert = Cert.load(contractAddress, client, cryptoKeyPair);
            String certificationID = getUUID();
            BigInteger type=getType(context);
            cert.sendCertificate(_uid,certificationID,context,type,ttl);
        } catch (Exception e) {
        }
    }

    public Tuple3<List<String>, List<String>, List<BigInteger>> listAllDone() throws Exception {
        //区块链交易函数,返回结构体数组
        String contractAddress = loadAssetAddr();
        Cert cert = Cert.load(contractAddress, client, cryptoKeyPair);
        return cert.getDone();
    }


    private BigInteger getType(String userInfo) {
        BigInteger level = new BigInteger("0");
        switch (userInfo){
            case "a":
                level = new BigInteger("1");
            case "b":
                level= new BigInteger("1");
            case "c":
                level= new BigInteger("1");
        }
        return level;
    }

    public static String getUUID(){
        UUID uuid= UUID.randomUUID();
        String str = uuid.toString();
        String uuidStr=str.replace("-", "");
        return uuidStr;
    }

}
