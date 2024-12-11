package ru.example.model;

public class Pet {
    private final Integer id;
    private final String name;
    private final Owner owner;

    private Pet(PetBuilder petBuilder) {
        this.id = petBuilder.id;
        this.name = petBuilder.name;
        this.owner = petBuilder.owner;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Owner getOwner() {
        return owner;
    }

    public static class PetBuilder {
        private Integer id;
        private String name;
        private Owner owner;

        public PetBuilder setId(Integer id) {
            this.id = id;
            return this;
        }

        public PetBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public PetBuilder setOwner(Owner owner) {
            this.owner = owner;
            return this;
        }

        public Pet build() {
            return new Pet(this);
        }
    }
}
