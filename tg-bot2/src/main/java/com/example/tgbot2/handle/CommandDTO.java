package com.example.tgbot2.handle;

import lombok.Data;

@Data
public class CommandDTO {
    private String commandName;
    private String commandQuery;
    private CommandExec commandExec;
    private String name;
}
