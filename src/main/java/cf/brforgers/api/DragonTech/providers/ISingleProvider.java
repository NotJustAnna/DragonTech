package cf.brforgers.api.DragonTech.providers;

public interface ISingleProvider<A> {
    A provideA();

    boolean canProvideA();
}
