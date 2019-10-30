package org.bbottema.loremipsumobjects.typefactories;

import org.bbottema.loremipsumobjects.ClassBindings;
import org.bbottema.loremipsumobjects.ClassUsageInfo;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Defines the interface for creating a dummy instance. A default implementation is provided for validation purposes (always returns true by
 * default).
 */
public abstract class LoremIpsumObjectFactory<T> {

	/**
	 * Default implementation returns true. Should be used when a factory knows beforehand when it will be unable to produce an instance of
	 * the requested type.
	 */
	public boolean isValidForType(final Class<? super T> clazz) {
		return true;
	}

	/**
	 * Starts a new chain for the creation of a dummy with an empty list of known class instances and empty list of exceptions.
	 *
	 * @param classBindings A list of bindings to which a factory may defer dummy creation to.
	 * @return See {@link #createLoremIpsumObject(Type[], Map, ClassBindings, List)}.
	 */
	@Nullable
	public final T createLoremIpsumObject(@Nullable final ClassBindings classBindings) {
		return createLoremIpsumObject(null, new HashMap<String, ClassUsageInfo<?>>(), classBindings, new ArrayList<Exception>());
	}

	/**
	 * Creates a new instance of one of the following: the requested type, a sub type of the requested type or, if the requested type is an
	 * interface definition, a suitable implementation. Should return <code>null</code> if nothing can be found.
	 * <p>
	 * If there are known exceptions (reflection exceptions when invoking a {@link Constructor} for example), don't throw them, but record
	 * them in the given <code>exceptions</code> list. Not all failures are counted towards complete failures.
	 *
	 * @param genericMetaData Can be <code>null</code>. Should be non-null when requested type is a {@link List} or {@link Map}.
	 * @param knownInstances  A list of previously created and populated objects for a specific type.
	 * @param classBindings   A list of bindings to which a factory may defer dummy creation to.
	 * @param exceptions      A list in which to store exceptions so they can be logged at some later point. This is done so, because not all
	 *                        exceptions are bad (in case a factory is trying to find a useful constructor, invocations are allowed to fail if a
	 *                        suitable constructor can be found otherwise).
	 * @return A new instance of the given type.
	 */
	@Nullable
	public abstract T createLoremIpsumObject(
			@Nullable Type[] genericMetaData,
			@Nullable Map<String, ClassUsageInfo<?>> knownInstances,
			@Nullable ClassBindings classBindings,
			@Nullable List<Exception> exceptions);
}