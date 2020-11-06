package ru.armd.pbk.core;

/**
 * Интерфейс, содержащий методы с информацие о том, кто и когда создал и изменил
 * объект.
 */
public interface IAuditable
	extends IHasCreater, IHasUpdater, IAuditableString {
}
