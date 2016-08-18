package cf.brforgers.mods.DragonTech.common.utils;

import cf.brforgers.api.DragonTech.providers.*;

public class SimpleProviders {
    public static class Single<T> implements IProvider<T>, ISingleProvider<T>, IReceiver<T>, ISingleReceiver<T> {
        private T obj;

        public Single() {
        }

        public Single(T obj1) {
            receiveA(obj1);
        }

        @Override
        public T provide() {
            return obj;
        }

        @Override
        public boolean canProvide() {
            return true;
        }

        @Override
        public void receive(T obj) {
            this.obj = obj;
        }

        @Override
        public T provideA() {
            return obj;
        }

        @Override
        public boolean canProvideA() {
            return true;
        }

        @Override
        public void receiveA(T obj1) {
            obj = obj1;
        }
    }

    public static class Double<A, B> extends Single<A> implements IDoubleProvider<A, B>, IDoubleReceiver<A, B> {
        private B obj2;

        public Double() {
        }

        public Double(A obj1, B obj2) {
            super(obj1);
            receiveB(obj2);
        }

        @Override
        public B provideB() {
            return obj2;
        }

        @Override
        public boolean canProvideB() {
            return true;
        }

        @Override
        public void receiveB(B obj2) {
            this.obj2 = obj2;
        }
    }

    public static class Triple<A, B, C> extends Double<A, B> implements ITripleProvider<A, B, C>, ITripleReceiver<A, B, C> {
        private C obj3;

        public Triple() {
        }

        public Triple(A obj1, B obj2, C obj3) {
            super(obj1, obj2);
            receiveC(obj3);
        }

        @Override
        public C provideC() {
            return obj3;
        }

        @Override
        public boolean canProvideC() {
            return true;
        }

        @Override
        public void receiveC(C obj3) {
            this.obj3 = obj3;
        }
    }
}
