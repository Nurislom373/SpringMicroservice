package org.khasanof;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 5/26/2024 4:54 AM
 */
public class RemoteServiceSimulator {

    private final long wait;

    public RemoteServiceSimulator(long wait) {
        this.wait = wait;
    }

    /**
     *
     * @return
     * @throws InterruptedException
     */
    public String execute() throws InterruptedException {
        Thread.sleep(wait);
        return "Success";
    }
}
