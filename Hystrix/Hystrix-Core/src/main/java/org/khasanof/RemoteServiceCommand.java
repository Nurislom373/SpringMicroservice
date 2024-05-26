package org.khasanof;

import com.netflix.hystrix.HystrixCommand;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 5/26/2024 4:55 AM
 */
public class RemoteServiceCommand extends HystrixCommand<String> {

    private final RemoteServiceSimulator remoteServiceSimulator;

    protected RemoteServiceCommand(Setter config, RemoteServiceSimulator remoteServiceSimulator) {
        super(config);
        this.remoteServiceSimulator = remoteServiceSimulator;
    }

    @Override
    protected String run() throws Exception {
        return remoteServiceSimulator.execute();
    }
}
