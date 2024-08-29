package org.khasanof.processor;

import org.khasanof.PersonEvent;

/**
 * @author Nurislom
 * @see org.khasanof.processor
 * @since 5/2/2024 3:49 PM
 */
public interface PersonProcessor {

    /**
     *
     * @param event
     * @return
     */
    PersonEvent save(PersonEvent event);
}
