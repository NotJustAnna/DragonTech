package cf.brforgers.api.DragonTech.providers;

public interface IProvider<T> {
    T provide();

    boolean canProvide();
}
