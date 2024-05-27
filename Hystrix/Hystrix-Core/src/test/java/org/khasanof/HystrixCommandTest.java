package org.khasanof;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 5/26/2024 4:46 AM
 */
public class HystrixCommandTest {

    /**
     *
     */
    @Test
    public void givenInputBobAndDefaultSettings_whenCommandExecuted_thenReturnHelloBob() {
        assertThat(new CommandHelloWorld("Bob").execute()).isEqualTo("Hello Bob!");
    }

    /**
     *
     */
    @Test
    public void synchronousExecutionTestShouldSuccess() {
        CommandHelloWorld commandHelloWorld = new CommandHelloWorld("Jeck");
        assertThat(commandHelloWorld.execute()).isEqualTo("Hello Jeck!");
    }

    /**
     *
     */
    @Test
    public void asynchronousExecutionTestShouldSuccess() throws ExecutionException, InterruptedException {
        CommandHelloWorld commandHelloWorld = new CommandHelloWorld("Jeck");
        Future<String> future = commandHelloWorld.queue();
        assertThat(future.get()).isEqualTo("Hello Jeck!");
    }

    /**
     *
     */
    @Test
    public void givenSvcTimeoutOf100AndDefaultSettings_whenRemoteSvcExecuted_thenReturnSuccess() {
        HystrixCommand.Setter config = HystrixCommand
                .Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("RemoteServiceGroup2"));

        assertThat(new RemoteServiceCommand(config, new RemoteServiceSimulator(100)).execute()).isEqualTo("Success");
    }

    /**
     *
     */
    @Test
    public void givenSvcTimeoutOf5000AndExecTimeoutOf10000_whenRemoteSvcExecuted_thenReturnSuccess() {
        HystrixCommand.Setter config = HystrixCommand
                .Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("RemoteServiceGroupTest4"));

        HystrixCommandProperties.Setter commandProperties = HystrixCommandProperties.Setter();
        commandProperties.withExecutionTimeoutInMilliseconds(10_000);
        config.andCommandPropertiesDefaults(commandProperties);

        assertThat(new RemoteServiceCommand(config, new RemoteServiceSimulator(500)).execute()).isEqualTo("Success");
    }

    /**
     *
     */
    @Test
    public void givenSvcTimeoutOf15000AndExecTimeoutOf5000_whenRemoteSvcExecuted_thenExpectHre() {
        HystrixCommand.Setter config = HystrixCommand
                .Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("RemoteServiceGroupTest5"));

        HystrixCommandProperties.Setter commandProperties = HystrixCommandProperties.Setter();
        commandProperties.withExecutionTimeoutInMilliseconds(5_000);
        config.andCommandPropertiesDefaults(commandProperties);

        assertThrows(HystrixRuntimeException.class, () -> new RemoteServiceCommand(config, new RemoteServiceSimulator(15_000)).execute());
    }

    /**
     *
     */
    @Test
    public void givenSvcTimeoutOf500AndExecTimeoutOf10000AndThreadPool_whenRemoteSvcExecuted_thenReturnSuccess() {
        HystrixCommand.Setter config = HystrixCommand
                .Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("RemoteServiceGroupThreadPool"));

        HystrixCommandProperties.Setter commandProperties = HystrixCommandProperties.Setter();
        commandProperties.withExecutionTimeoutInMilliseconds(10_000);
        config.andCommandPropertiesDefaults(commandProperties);
        config.andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                .withMaxQueueSize(10)
                .withCoreSize(3)
                .withQueueSizeRejectionThreshold(10));

        assertThat(new RemoteServiceCommand(config, new RemoteServiceSimulator(500)).execute()).isEqualTo("Success");
    }

    /**
     *
     * @throws InterruptedException
     */
    @Test
    public void givenCircuitBreakerSetup_whenRemoteSvcCmdExecuted_thenReturnSuccess() throws InterruptedException {
        HystrixCommand.Setter config = HystrixCommand
                .Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("RemoteServiceGroupCircuitBreaker"));

        HystrixCommandProperties.Setter properties = HystrixCommandProperties.Setter();
        properties.withExecutionTimeoutInMilliseconds(1000);
        properties.withCircuitBreakerSleepWindowInMilliseconds(4000);
        properties.withExecutionIsolationStrategy
                (HystrixCommandProperties.ExecutionIsolationStrategy.THREAD);
        properties.withCircuitBreakerEnabled(true);
        properties.withCircuitBreakerRequestVolumeThreshold(1);

        config.andCommandPropertiesDefaults(properties);
        config.andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                .withMaxQueueSize(1)
                .withCoreSize(1)
                .withQueueSizeRejectionThreshold(1));

        assertThat(this.invokeRemoteService(config, 10_000)).isEqualTo(null);
        assertThat(this.invokeRemoteService(config, 10_000)).isEqualTo(null);
        assertThat(this.invokeRemoteService(config, 10_000)).isEqualTo(null);

        Thread.sleep(5000);

        assertThat(new RemoteServiceCommand(config, new RemoteServiceSimulator(500)).execute()).isEqualTo("Success");
        assertThat(new RemoteServiceCommand(config, new RemoteServiceSimulator(500)).execute()).isEqualTo("Success");
        assertThat(new RemoteServiceCommand(config, new RemoteServiceSimulator(500)).execute()).isEqualTo("Success");
    }

    public String invokeRemoteService(HystrixCommand.Setter config, int timeout) {

        String response = null;

        try {
            response = new RemoteServiceCommand(config, new RemoteServiceSimulator(timeout)).execute();
        } catch (HystrixRuntimeException ex) {
            System.out.println("ex = " + ex);
        }

        return response;
    }
}
