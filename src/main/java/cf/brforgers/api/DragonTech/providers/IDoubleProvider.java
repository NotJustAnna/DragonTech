package cf.brforgers.api.DragonTech.providers;

public interface IDoubleProvider<A, B> extends ISingleProvider<A> {
    B provideB();

    boolean canProvideB();
}
