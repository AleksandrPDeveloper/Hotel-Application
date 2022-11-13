package service;

class ItemAlreadyExistsException extends Exception{
    public ItemAlreadyExistsException(String msg){
        super(msg);
    }
}
