package ru.hometask1;

public class ApplicationContextHolder {
    private static final class Holder {
        private static final ApplicationContext INSTANCE = new ApplicationContext("ru.hometask1");
    }

    public static ApplicationContext getInstance() {
        return Holder.INSTANCE;
    }
}
