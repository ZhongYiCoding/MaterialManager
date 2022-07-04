package com.example.blocklink.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.sdk.abi.FunctionReturnDecoder;
import org.fisco.bcos.sdk.abi.TypeReference;
import org.fisco.bcos.sdk.abi.datatypes.Bool;
import org.fisco.bcos.sdk.abi.datatypes.DynamicArray;
import org.fisco.bcos.sdk.abi.datatypes.Function;
import org.fisco.bcos.sdk.abi.datatypes.Type;
import org.fisco.bcos.sdk.abi.datatypes.Utf8String;
import org.fisco.bcos.sdk.abi.datatypes.generated.Uint256;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple1;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple3;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple5;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.contract.Contract;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.model.CryptoType;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;

@SuppressWarnings("unchecked")
public class Cert extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b506000600481905550610ec0806100286000396000f300608060405260043610610057576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806343438f3d1461005c578063474ffae5146100995780637e420bad146100d6575b600080fd5b34801561006857600080fd5b50610083600480360361007e91908101906109d1565b610103565b6040516100909190610d16565b60405180910390f35b3480156100a557600080fd5b506100c060048036036100bb9190810190610aa8565b610544565b6040516100cd9190610d16565b60405180910390f35b3480156100e257600080fd5b506100eb610614565b6040516100fa93929190610cca565b60405180910390f35b60006040518060000190506040518091039020600019166000876040518082805190602001908083835b602083101515610152578051825260208201915060208101905060208303925061012d565b6001836020036101000a03801982511681845116808217855250505050505090500191505090815260200160405180910390206000016040516020016101989190610d31565b6040516020818303038152906040526040518082805190602001908083835b6020831015156101dc57805182526020820191506020810190506020830392506101b7565b6001836020036101000a038019825116818451168082178552505050505050905001915050604051809103902060001916141561045b57606060405190810160405280868152602001848152602001838152506000876040518082805190602001908083835b6020831015156102675780518252602082019150602081019050602083039250610242565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051809103902060008201518160000190805190602001906102b6929190610842565b506020820151816001015560408201518160020190805190602001906102dd929190610842565b509050506102ec868585610544565b50846040518082805190602001908083835b60208310151561032357805182526020820191506020810190506020830392506102fe565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020600019166000876040518082805190602001908083835b60208310151561038c5780518252602082019150602081019050602083039250610367565b6001836020036101000a03801982511681845116808217855250505050505090500191505090815260200160405180910390206000016040516020016103d29190610d31565b6040516020818303038152906040526040518082805190602001908083835b60208310151561041657805182526020820191506020810190506020830392506103f1565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020600019161415610456576000905061053b565b610536565b606060405190810160405280868152602001848152602001838152506000876040518082805190602001908083835b6020831015156104af578051825260208201915060208101905060208303925061048a565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051809103902060008201518160000190805190602001906104fe929190610842565b50602082015181600101556040820151816002019080519060200190610525929190610842565b50905050610534868585610544565b505b600190505b95945050505050565b600080600454905060018590806001815401808255809150509060018203906000526020600020016000909192909190915090805190602001906105899291906108c2565b505060028490806001815401808255809150509060018203906000526020600020016000909192909190915090805190602001906105c89291906108c2565b5050600383908060018154018082558091505090600182039060005260206000200160009091929091909150555060046000815480929190600101919050555060019150509392505050565b606080606060016002600382805480602002602001604051908101604052809291908181526020016000905b828210156106fc578382906000526020600020018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156106e85780601f106106bd576101008083540402835291602001916106e8565b820191906000526020600020905b8154815290600101906020018083116106cb57829003601f168201915b505050505081526020019060010190610640565b50505050925081805480602002602001604051908101604052809291908181526020016000905b828210156107df578382906000526020600020018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156107cb5780601f106107a0576101008083540402835291602001916107cb565b820191906000526020600020905b8154815290600101906020018083116107ae57829003601f168201915b505050505081526020019060010190610723565b5050505091508080548060200260200160405190810160405280929190818152602001828054801561083057602002820191906000526020600020905b81548152602001906001019080831161081c575b50505050509050925092509250909192565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061088357805160ff19168380011785556108b1565b828001600101855582156108b1579182015b828111156108b0578251825591602001919060010190610895565b5b5090506108be9190610942565b5090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061090357805160ff1916838001178555610931565b82800160010185558215610931579182015b82811115610930578251825591602001919060010190610915565b5b50905061093e9190610942565b5090565b61096491905b80821115610960576000816000905550600101610948565b5090565b90565b600082601f830112151561097a57600080fd5b813561098d61098882610d80565b610d53565b915080825260208301602083018583830111156109a957600080fd5b6109b4838284610e33565b50505092915050565b60006109c98235610e29565b905092915050565b600080600080600060a086880312156109e957600080fd5b600086013567ffffffffffffffff811115610a0357600080fd5b610a0f88828901610967565b955050602086013567ffffffffffffffff811115610a2c57600080fd5b610a3888828901610967565b945050604086013567ffffffffffffffff811115610a5557600080fd5b610a6188828901610967565b9350506060610a72888289016109bd565b925050608086013567ffffffffffffffff811115610a8f57600080fd5b610a9b88828901610967565b9150509295509295909350565b600080600060608486031215610abd57600080fd5b600084013567ffffffffffffffff811115610ad757600080fd5b610ae386828701610967565b935050602084013567ffffffffffffffff811115610b0057600080fd5b610b0c86828701610967565b9250506040610b1d868287016109bd565b9150509250925092565b6000610b3282610dd8565b80845260208401935083602082028501610b4b85610dac565b60005b84811015610b84578383038852610b66838351610bff565b9250610b7182610df9565b9150602088019750600181019050610b4e565b508196508694505050505092915050565b6000610ba082610de3565b808452602084019350610bb283610db9565b60005b82811015610be457610bc8868351610cbb565b610bd182610e06565b9150602086019550600181019050610bb5565b50849250505092915050565b610bf981610e13565b82525050565b6000610c0a82610dee565b808452610c1e816020860160208601610e42565b610c2781610e75565b602085010191505092915050565b600081546001811660008114610c525760018114610c7257610cb3565b607f600283041680865260ff198316602087015260408601935050610cb3565b60028204808652602086019550610c8885610dc6565b60005b82811015610caa57815481890152600182019150602081019050610c8b565b80880195505050505b505092915050565b610cc481610e1f565b82525050565b60006060820190508181036000830152610ce48186610b27565b90508181036020830152610cf88185610b27565b90508181036040830152610d0c8184610b95565b9050949350505050565b6000602082019050610d2b6000830184610bf0565b92915050565b60006020820190508181036000830152610d4b8184610c35565b905092915050565b6000604051905081810181811067ffffffffffffffff82111715610d7657600080fd5b8060405250919050565b600067ffffffffffffffff821115610d9757600080fd5b601f19601f8301169050602081019050919050565b6000602082019050919050565b6000602082019050919050565b60008160005260206000209050919050565b600081519050919050565b600081519050919050565b600081519050919050565b6000602082019050919050565b6000602082019050919050565b60008115159050919050565b6000819050919050565b6000819050919050565b82818337600083830152505050565b60005b83811015610e60578082015181840152602081019050610e45565b83811115610e6f576000848401525b50505050565b6000601f19601f83011690509190505600a265627a7a723058207aa71fe0198abd0c0409a07178b4485d1203400c7597a1d8c190f1d383034c156c6578706572696d656e74616cf50037"};

    public static final String BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {"608060405234801561001057600080fd5b506000600481905550610ec0806100286000396000f300608060405260043610610057576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806343438f3d1461005c578063474ffae5146100995780637e420bad146100d6575b600080fd5b34801561006857600080fd5b50610083600480360361007e91908101906109d1565b610103565b6040516100909190610d16565b60405180910390f35b3480156100a557600080fd5b506100c060048036036100bb9190810190610aa8565b610544565b6040516100cd9190610d16565b60405180910390f35b3480156100e257600080fd5b506100eb610614565b6040516100fa93929190610cca565b60405180910390f35b60006040518060000190506040518091039020600019166000876040518082805190602001908083835b602083101515610152578051825260208201915060208101905060208303925061012d565b6001836020036101000a03801982511681845116808217855250505050505090500191505090815260200160405180910390206000016040516020016101989190610d31565b6040516020818303038152906040526040518082805190602001908083835b6020831015156101dc57805182526020820191506020810190506020830392506101b7565b6001836020036101000a038019825116818451168082178552505050505050905001915050604051809103902060001916141561045b57606060405190810160405280868152602001848152602001838152506000876040518082805190602001908083835b6020831015156102675780518252602082019150602081019050602083039250610242565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051809103902060008201518160000190805190602001906102b6929190610842565b506020820151816001015560408201518160020190805190602001906102dd929190610842565b509050506102ec868585610544565b50846040518082805190602001908083835b60208310151561032357805182526020820191506020810190506020830392506102fe565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020600019166000876040518082805190602001908083835b60208310151561038c5780518252602082019150602081019050602083039250610367565b6001836020036101000a03801982511681845116808217855250505050505090500191505090815260200160405180910390206000016040516020016103d29190610d31565b6040516020818303038152906040526040518082805190602001908083835b60208310151561041657805182526020820191506020810190506020830392506103f1565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020600019161415610456576000905061053b565b610536565b606060405190810160405280868152602001848152602001838152506000876040518082805190602001908083835b6020831015156104af578051825260208201915060208101905060208303925061048a565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051809103902060008201518160000190805190602001906104fe929190610842565b50602082015181600101556040820151816002019080519060200190610525929190610842565b50905050610534868585610544565b505b600190505b95945050505050565b600080600454905060018590806001815401808255809150509060018203906000526020600020016000909192909190915090805190602001906105899291906108c2565b505060028490806001815401808255809150509060018203906000526020600020016000909192909190915090805190602001906105c89291906108c2565b5050600383908060018154018082558091505090600182039060005260206000200160009091929091909150555060046000815480929190600101919050555060019150509392505050565b606080606060016002600382805480602002602001604051908101604052809291908181526020016000905b828210156106fc578382906000526020600020018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156106e85780601f106106bd576101008083540402835291602001916106e8565b820191906000526020600020905b8154815290600101906020018083116106cb57829003601f168201915b505050505081526020019060010190610640565b50505050925081805480602002602001604051908101604052809291908181526020016000905b828210156107df578382906000526020600020018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156107cb5780601f106107a0576101008083540402835291602001916107cb565b820191906000526020600020905b8154815290600101906020018083116107ae57829003601f168201915b505050505081526020019060010190610723565b5050505091508080548060200260200160405190810160405280929190818152602001828054801561083057602002820191906000526020600020905b81548152602001906001019080831161081c575b50505050509050925092509250909192565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061088357805160ff19168380011785556108b1565b828001600101855582156108b1579182015b828111156108b0578251825591602001919060010190610895565b5b5090506108be9190610942565b5090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061090357805160ff1916838001178555610931565b82800160010185558215610931579182015b82811115610930578251825591602001919060010190610915565b5b50905061093e9190610942565b5090565b61096491905b80821115610960576000816000905550600101610948565b5090565b90565b600082601f830112151561097a57600080fd5b813561098d61098882610d80565b610d53565b915080825260208301602083018583830111156109a957600080fd5b6109b4838284610e33565b50505092915050565b60006109c98235610e29565b905092915050565b600080600080600060a086880312156109e957600080fd5b600086013567ffffffffffffffff811115610a0357600080fd5b610a0f88828901610967565b955050602086013567ffffffffffffffff811115610a2c57600080fd5b610a3888828901610967565b945050604086013567ffffffffffffffff811115610a5557600080fd5b610a6188828901610967565b9350506060610a72888289016109bd565b925050608086013567ffffffffffffffff811115610a8f57600080fd5b610a9b88828901610967565b9150509295509295909350565b600080600060608486031215610abd57600080fd5b600084013567ffffffffffffffff811115610ad757600080fd5b610ae386828701610967565b935050602084013567ffffffffffffffff811115610b0057600080fd5b610b0c86828701610967565b9250506040610b1d868287016109bd565b9150509250925092565b6000610b3282610dd8565b80845260208401935083602082028501610b4b85610dac565b60005b84811015610b84578383038852610b66838351610bff565b9250610b7182610df9565b9150602088019750600181019050610b4e565b508196508694505050505092915050565b6000610ba082610de3565b808452602084019350610bb283610db9565b60005b82811015610be457610bc8868351610cbb565b610bd182610e06565b9150602086019550600181019050610bb5565b50849250505092915050565b610bf981610e13565b82525050565b6000610c0a82610dee565b808452610c1e816020860160208601610e42565b610c2781610e75565b602085010191505092915050565b600081546001811660008114610c525760018114610c7257610cb3565b607f600283041680865260ff198316602087015260408601935050610cb3565b60028204808652602086019550610c8885610dc6565b60005b82811015610caa57815481890152600182019150602081019050610c8b565b80880195505050505b505092915050565b610cc481610e1f565b82525050565b60006060820190508181036000830152610ce48186610b27565b90508181036020830152610cf88185610b27565b90508181036040830152610d0c8184610b95565b9050949350505050565b6000602082019050610d2b6000830184610bf0565b92915050565b60006020820190508181036000830152610d4b8184610c35565b905092915050565b6000604051905081810181811067ffffffffffffffff82111715610d7657600080fd5b8060405250919050565b600067ffffffffffffffff821115610d9757600080fd5b601f19601f8301169050602081019050919050565b6000602082019050919050565b6000602082019050919050565b60008160005260206000209050919050565b600081519050919050565b600081519050919050565b600081519050919050565b6000602082019050919050565b6000602082019050919050565b60008115159050919050565b6000819050919050565b6000819050919050565b82818337600083830152505050565b60005b83811015610e60578082015181840152602081019050610e45565b83811115610e6f576000848401525b50505050565b6000601f19601f83011690509190505600a265627a7a723058207aa71fe0198abd0c0409a07178b4485d1203400c7597a1d8c190f1d383034c156c6578706572696d656e74616cf50037"};

    public static final String SM_BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"name\":\"sendCertificate\",\"type\":\"function\",\"constant\":false,\"payable\":false,\"anonymous\":false,\"stateMutability\":\"nonpayable\",\"inputs\":[{\"name\":\"_userID\",\"type\":\"string\",\"indexed\":false,\"components\":null,\"typeAsString\":\"string\"},{\"name\":\"_certificationID\",\"type\":\"string\",\"indexed\":false,\"components\":null,\"typeAsString\":\"string\"},{\"name\":\"_info\",\"type\":\"string\",\"indexed\":false,\"components\":null,\"typeAsString\":\"string\"},{\"name\":\"_cType\",\"type\":\"uint256\",\"indexed\":false,\"components\":null,\"typeAsString\":\"uint256\"},{\"name\":\"_ttl\",\"type\":\"string\",\"indexed\":false,\"components\":null,\"typeAsString\":\"string\"}],\"outputs\":[{\"name\":\"\",\"type\":\"bool\",\"indexed\":false,\"components\":null,\"typeAsString\":\"bool\"}],\"methodSignatureAsString\":\"sendCertificate(string,string,string,uint256,string)\"},{\"name\":\"sendDone\",\"type\":\"function\",\"constant\":false,\"payable\":false,\"anonymous\":false,\"stateMutability\":\"nonpayable\",\"inputs\":[{\"name\":\"_userID\",\"type\":\"string\",\"indexed\":false,\"components\":null,\"typeAsString\":\"string\"},{\"name\":\"_info\",\"type\":\"string\",\"indexed\":false,\"components\":null,\"typeAsString\":\"string\"},{\"name\":\"_cType\",\"type\":\"uint256\",\"indexed\":false,\"components\":null,\"typeAsString\":\"uint256\"}],\"outputs\":[{\"name\":\"\",\"type\":\"bool\",\"indexed\":false,\"components\":null,\"typeAsString\":\"bool\"}],\"methodSignatureAsString\":\"sendDone(string,string,uint256)\"},{\"name\":\"getDone\",\"type\":\"function\",\"constant\":true,\"payable\":false,\"anonymous\":false,\"stateMutability\":\"view\",\"inputs\":[],\"outputs\":[{\"name\":\"\",\"type\":\"string[]\",\"indexed\":false,\"components\":null,\"typeAsString\":\"string[]\"},{\"name\":\"\",\"type\":\"string[]\",\"indexed\":false,\"components\":null,\"typeAsString\":\"string[]\"},{\"name\":\"\",\"type\":\"uint256[]\",\"indexed\":false,\"components\":null,\"typeAsString\":\"uint256[]\"}],\"methodSignatureAsString\":\"getDone()\"},{\"name\":null,\"type\":\"constructor\",\"constant\":false,\"payable\":false,\"anonymous\":false,\"stateMutability\":\"nonpayable\",\"inputs\":[],\"outputs\":null,\"methodSignatureAsString\":\"null()\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_SENDCERTIFICATE = "sendCertificate";

    public static final String FUNC_SENDDONE = "sendDone";

    public static final String FUNC_GETDONE = "getDone";

    protected Cert(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public TransactionReceipt sendCertificate(String _userID, String _certificationID, String _info, BigInteger _cType, String _ttl) {
        final Function function = new Function(
                FUNC_SENDCERTIFICATE,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(_userID),
                        new org.fisco.bcos.sdk.abi.datatypes.Utf8String(_certificationID),
                        new org.fisco.bcos.sdk.abi.datatypes.Utf8String(_info),
                        new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(_cType),
                        new org.fisco.bcos.sdk.abi.datatypes.Utf8String(_ttl)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void sendCertificate(String _userID, String _certificationID, String _info, BigInteger _cType, String _ttl, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_SENDCERTIFICATE,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(_userID),
                        new org.fisco.bcos.sdk.abi.datatypes.Utf8String(_certificationID),
                        new org.fisco.bcos.sdk.abi.datatypes.Utf8String(_info),
                        new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(_cType),
                        new org.fisco.bcos.sdk.abi.datatypes.Utf8String(_ttl)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForSendCertificate(String _userID, String _certificationID, String _info, BigInteger _cType, String _ttl) {
        final Function function = new Function(
                FUNC_SENDCERTIFICATE,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(_userID),
                        new org.fisco.bcos.sdk.abi.datatypes.Utf8String(_certificationID),
                        new org.fisco.bcos.sdk.abi.datatypes.Utf8String(_info),
                        new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(_cType),
                        new org.fisco.bcos.sdk.abi.datatypes.Utf8String(_ttl)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple5<String, String, String, BigInteger, String> getSendCertificateInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_SENDCERTIFICATE,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple5<String, String, String, BigInteger, String>(

                (String) results.get(0).getValue(),
                (String) results.get(1).getValue(),
                (String) results.get(2).getValue(),
                (BigInteger) results.get(3).getValue(),
                (String) results.get(4).getValue()
        );
    }

    public Tuple1<Boolean> getSendCertificateOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_SENDCERTIFICATE,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
        );
    }

    public TransactionReceipt sendDone(String _userID, String _info, BigInteger _cType) {
        final Function function = new Function(
                FUNC_SENDDONE,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(_userID),
                        new org.fisco.bcos.sdk.abi.datatypes.Utf8String(_info),
                        new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(_cType)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void sendDone(String _userID, String _info, BigInteger _cType, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_SENDDONE,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(_userID),
                        new org.fisco.bcos.sdk.abi.datatypes.Utf8String(_info),
                        new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(_cType)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForSendDone(String _userID, String _info, BigInteger _cType) {
        final Function function = new Function(
                FUNC_SENDDONE,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(_userID),
                        new org.fisco.bcos.sdk.abi.datatypes.Utf8String(_info),
                        new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(_cType)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple3<String, String, BigInteger> getSendDoneInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_SENDDONE,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple3<String, String, BigInteger>(

                (String) results.get(0).getValue(),
                (String) results.get(1).getValue(),
                (BigInteger) results.get(2).getValue()
        );
    }

    public Tuple1<Boolean> getSendDoneOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_SENDDONE,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
        );
    }

    public Tuple3<List<String>, List<String>, List<BigInteger>> getDone() throws ContractException {
        final Function function = new Function(FUNC_GETDONE,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Utf8String>>() {}, new TypeReference<DynamicArray<Utf8String>>() {}, new TypeReference<DynamicArray<Uint256>>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple3<List<String>, List<String>, List<BigInteger>>(
                convertToNative((List<Utf8String>) results.get(0).getValue()),
                convertToNative((List<Utf8String>) results.get(1).getValue()),
                convertToNative((List<Uint256>) results.get(2).getValue()));
    }

    public static Cert load(String contractAddress, Client client, CryptoKeyPair credential) {
        return new Cert(contractAddress, client, credential);
    }

    public static Cert deploy(Client client, CryptoKeyPair credential) throws ContractException {
        return deploy(Cert.class, client, credential, getBinary(client.getCryptoSuite()), "");
    }
}