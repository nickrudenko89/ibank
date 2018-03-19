package Enums;

public enum UserTypeEnum {
    EMPLOYEE, CLIENT;

    public int getIndex() {
        return this.ordinal() + 1;
    }
}
