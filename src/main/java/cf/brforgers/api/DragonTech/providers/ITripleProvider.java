package cf.brforgers.api.DragonTech.providers;

public interface ITripleProvider<A, B, C> extends IDoubleProvider<A, B> {
    C provideC();

    boolean canProvideC();
}
