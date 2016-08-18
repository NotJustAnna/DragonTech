package cf.brforgers.api.DragonTech.providers;

public interface ITripleReceiver<A, B, C> extends IDoubleProvider<A, B> {
    void receiveC(C obj3);
}
