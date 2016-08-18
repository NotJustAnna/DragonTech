package cf.brforgers.api.DragonTech.providers;

public interface IDoubleReceiver<A, B> extends ISingleReceiver<A> {
    void receiveB(B obj2);
}
