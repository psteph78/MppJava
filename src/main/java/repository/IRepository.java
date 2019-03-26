package repository;

public interface IRepository<E> {
    void add(E e);
    void update(E e);
    void delete(E e);
    Iterable<E> findAll();
}
