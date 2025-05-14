package com.exemple;

public class Family {
    private Human mother;
    private Human father;
    private Human[] children;
    private Pet pet;


    public Family(Human mother, Human father) {
        this.mother = mother;
        this.father = father;
        this.children = new Human[0];
        this.mother.setFamily(this);
        this.father.setFamily(this);
    }


    public Human getMother() {
        return mother;
    }

    public void setMother(Human mother) {
        this.mother = mother;
    }

    public Human getFather() {
        return father;
    }

    public void setFather(Human father) {
        this.father = father;
    }

    public Human[] getChildren() {
        return children;
    }

    public void setChildren(Human[] children) {
        this.children = children;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Mother: ").append(mother).append("\n");
        sb.append("Father: ").append(father).append("\n");
        sb.append("Children: ");
        if (children == null || children.length == 0) {
            sb.append("No children\n");
        } else {
            for (Human child : children) {
                sb.append(child).append("\n");
            }
        }
        sb.append("Pet: ");
        if (pet == null) {
            sb.append("No pet");
        } else {
            sb.append(pet);
        }
        return sb.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Finalizing object: " + this);
        super.finalize();
    }


    public void addChild(Human child) {
        if (children == null) {
            children = new Human[1];
            children[0] = child;
        } else {
            Human[] newChildren = new Human[children.length + 1];
            System.arraycopy(children, 0, newChildren, 0, children.length);
            newChildren[children.length] = child;
            children = newChildren;
        }
        child.setFamily(this);
    }


    public boolean deleteChild(Human child) {
        if (children == null || children.length == 0) {
            return false;
        }

        int index = -1;
        for (int i = 0; i < children.length; i++) {
            if (children[i].equals(child)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            return false;
        }

        return deleteChild(index);
    }


    public boolean deleteChild(int index) {
        if (children == null || index < 0 || index >= children.length) {
            return false;
        }

        Human[] newChildren = new Human[children.length - 1];
        for (int i = 0, j = 0; i < children.length; i++) {
            if (i != index) {
                newChildren[j++] = children[i];
            } else {
                children[i].setFamily(null); // Remove family reference
            }
        }

        children = newChildren;
        return true;
    }


    public int countFamily() {
        int count = 2; // Mother and father
        if (children != null) {
            count += children.length;
        }
        return count;
    }

}
