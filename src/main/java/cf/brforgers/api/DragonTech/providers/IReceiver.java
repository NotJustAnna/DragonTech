package cf.brforgers.api.DragonTech.providers;

public interface IReceiver<T> {
    void receive(T obj);
}
