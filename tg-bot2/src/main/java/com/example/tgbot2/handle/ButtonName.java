package com.example.tgbot2.handle;

public enum ButtonName {

    RD_BUTTON("Кнопка1"),
    HYDRANT_MAP_BUTTON("Кнопка2"),
    SCHEDULE_BUTTON("Кнопка3"),
    KNOWLEDGE_BASE("Кнопка4"),
    RESPONSIBILITIES("Кнопка5"),
    CHARACTERISTICS1("Кнопка6"),
    CHARACTERISTICS2("Кнопка7"),
    CHARACTERISTICS3("Кнопка8"),
    CHARACTERISTICS4("Кнопка9"),
    DEVELOPER_BUTTON("тут тоже чтото");

    private final String buttonName;

    ButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getButtonName() {
        return buttonName;
    }
}