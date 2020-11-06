package ru.armd.pbk.core.domain;

import ru.armd.pbk.core.IAuditable;
import ru.armd.pbk.core.IHasId;
import ru.armd.pbk.core.IHasIsDelete;

/**
 * Интерфейс для всех domain классов.
 */
public interface IDomain
	extends IHasId, IHasIsDelete, IAuditable {
}
