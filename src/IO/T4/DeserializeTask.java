package IO.T4;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Objects;

public class DeserializeTask {
    
    /**
     * Из переданного массива байт восстановит массив объектов Animal.
     * Массив байт устроен следующим образом.
     * Сначала идет число типа int, записанное при помощи ObjectOutputStream.writeInt(size).
     * Далее подряд записано указанное количество объектов типа Animal,
     * сериализованных при помощи ObjectOutputStream.writeObject(animal)
     * На все исключения пробрасывать IllegalArgumentException
     * @param data массив байт
     * @return
     */
    public static Animal[] deserializeAnimalArray(byte[] data) {
        Animal[] animals;
        try (ObjectInputStream oin = new ObjectInputStream(new ByteArrayInputStream(data))) {
            int count = oin.readInt();
            animals = new Animal[count];
            for (int i = 0; i < count; i++) {
                animals[i] = (Animal) oin.readObject();
            }
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
        return animals;
    }

    public static class Animal implements Serializable {
        private final String name;

        public Animal(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Animal) {
                return Objects.equals(name, ((Animal) obj).name);
            }
            return false;
        }
    }
}
