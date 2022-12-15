package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.contracts.Product;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.StaticGasProvider;
import org.web3j.utils.Numeric;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

@ApplicationScoped
public class BlockchainServiceImpl implements BlockchainService{

    public static final String PUBLIC_KEY = "0x5d4ac65059bd3b573777ebac62df0dff49375785";
    public static final String PRIVATE_KEY = "c8f64de10d4669efe94387917d58dbad116abf7a8bd0be01530956c049dbf457";
    public static final String CONTRACT_ADDRESS = "0x6A08449C90424141b138d459baDC0850f55BC01e";

    @Override
    public String getFunctionABI(fr.pantheonsorbonne.ufr27.miage.dto.Product productData) throws ExecutionException, InterruptedException, IOException {

        String rpcEndpoint = "http://localhost:7545";
        Web3j web3j = Web3j.build(new HttpService(rpcEndpoint));

        EthBlockNumber blockNumber = web3j.ethBlockNumber().sendAsync().get();
        System.out.println(" The Block Number is: " + blockNumber.getBlockNumber().toString());

        Credentials credentials = Credentials.create(PRIVATE_KEY);

        Product product = Product.load(CONTRACT_ADDRESS, web3j, credentials, new StaticGasProvider(new BigInteger("20000000000"), new BigInteger("300000")));

        return product.safeMint(PUBLIC_KEY, new BigInteger(String.valueOf(productData.getId())), "http://localhost:8083/product/"+productData.getId()).encodeFunctionCall();

    }

    private static BigInteger getNonce(Web3j web3j) throws InterruptedException, ExecutionException {
        EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(PUBLIC_KEY, DefaultBlockParameterName.LATEST).sendAsync().get();
        return ethGetTransactionCount.getTransactionCount();
    }
}
