package ru.example.model;

public class Owner {
    private final Integer id;
    private final String name;
    private final Pet pet;

    private Owner(OwnerBuilder ownerBuilder) {
        this.id = ownerBuilder.id;
        this.name = ownerBuilder.name;
        this.pet = ownerBuilder.pet;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Pet getPet() {
        return pet;
    }

    public static class OwnerBuilder{
        private Integer id;
        private String name;
        private Pet pet;

        public OwnerBuilder setId(Integer id) {
            this.id = id;
            return this;
        }

        public OwnerBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public OwnerBuilder setPet(Pet pet) {
            this.pet = pet;
            return this;
        }

        public Owner build() {
            return new Owner(this);
        }
    }
}
