package me.my_library_system.common.exception;

public class EntityNotFoundException extends LibraryException {
    public EntityNotFoundException(String entityName, Object id) {
        super(ErrorCode.ENTITY_NOT_FOUND, String.format("%s를 찾을 수 없습니다. id=%s", entityName, id));
    }
}
