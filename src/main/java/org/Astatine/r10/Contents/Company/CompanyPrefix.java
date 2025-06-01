package org.Astatine.r10.Contents.Company;

import org.Astatine.r10.Contents.EventRegister;

import io.papermc.paper.event.player.AsyncChatEvent;

public class CompanyPrefix implements EventRegister {

    private final AsyncChatEvent event;

    public CompanyPrefix(AsyncChatEvent event) {
        this.event = event;

        init();
        execute();
    }

    @Override
    public void init() {
        
    }
    
    @Override
    public void execute() {
        
    }
}