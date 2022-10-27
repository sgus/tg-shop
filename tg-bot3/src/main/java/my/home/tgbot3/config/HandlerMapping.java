package my.home.tgbot3.config;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.handler.AbstractHandlerMethodMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
public class HandlerMapping {


//    //тут будут вызываться эндпоинты
//    //так же происходить чек на существующие эндпоинты
//    String getHandler(String request) throws Exception{ //входящий запрос с телеги
//        List<HandlerMethod> matches = new ArrayList<>();
//    };
//
//
//    class MappingRegistry {
//
//        private final Map<T, AbstractHandlerMethodMapping.MappingRegistration<T>> registry = new HashMap<>();
//
//        private final MultiValueMap<String, T> pathLookup = new LinkedMultiValueMap<>();
//
//        private final Map<String, List<org.springframework.web.method.HandlerMethod>> nameLookup = new ConcurrentHashMap<>();
//
//        private final Map<org.springframework.web.method.HandlerMethod, CorsConfiguration> corsLookup = new ConcurrentHashMap<>();
//
//        private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
//
//        /**
//         * Return all registrations.
//         * @since 5.3
//         */
//        public Map<T, AbstractHandlerMethodMapping.MappingRegistration<T>> getRegistrations() {
//            return this.registry;
//        }
//
//        /**
//         * Return matches for the given URL path. Not thread-safe.
//         * @see #acquireReadLock()
//         */
//        @Nullable
//        public List<T> getMappingsByDirectPath(String urlPath) {
//            return this.pathLookup.get(urlPath);
//        }
//
//        /**
//         * Return handler methods by mapping name. Thread-safe for concurrent use.
//         */
//        public List<org.springframework.web.method.HandlerMethod> getHandlerMethodsByMappingName(String mappingName) {
//            return this.nameLookup.get(mappingName);
//        }
//
//        /**
//         * Return CORS configuration. Thread-safe for concurrent use.
//         */
//        @Nullable
//        public CorsConfiguration getCorsConfiguration(org.springframework.web.method.HandlerMethod handlerMethod) {
//            org.springframework.web.method.HandlerMethod original = handlerMethod.getResolvedFromHandlerMethod();
//            return this.corsLookup.get(original != null ? original : handlerMethod);
//        }
//
//        /**
//         * Acquire the read lock when using getMappings and getMappingsByUrl.
//         */
//        public void acquireReadLock() {
//            this.readWriteLock.readLock().lock();
//        }
//
//        /**
//         * Release the read lock after using getMappings and getMappingsByUrl.
//         */
//        public void releaseReadLock() {
//            this.readWriteLock.readLock().unlock();
//        }
//
//        public void register(T mapping, Object handler, Method method) {
//            this.readWriteLock.writeLock().lock();
//            try {
//                org.springframework.web.method.HandlerMethod handlerMethod = createHandlerMethod(handler, method);
//                validateMethodMapping(handlerMethod, mapping);
//
//                Set<String> directPaths = AbstractHandlerMethodMapping.this.getDirectPaths(mapping);
//                for (String path : directPaths) {
//                    this.pathLookup.add(path, mapping);
//                }
//
//                String name = null;
//                if (getNamingStrategy() != null) {
//                    name = getNamingStrategy().getName(handlerMethod, mapping);
//                    addMappingName(name, handlerMethod);
//                }
//
//                CorsConfiguration corsConfig = initCorsConfiguration(handler, method, mapping);
//                if (corsConfig != null) {
//                    corsConfig.validateAllowCredentials();
//                    this.corsLookup.put(handlerMethod, corsConfig);
//                }
//
//                this.registry.put(mapping,
//                        new AbstractHandlerMethodMapping.MappingRegistration<>(mapping, handlerMethod, directPaths, name, corsConfig != null));
//            }
//            finally {
//                this.readWriteLock.writeLock().unlock();
//            }
//        }
//
//        private void validateMethodMapping(org.springframework.web.method.HandlerMethod handlerMethod, T mapping) {
//            AbstractHandlerMethodMapping.MappingRegistration<T> registration = this.registry.get(mapping);
//            org.springframework.web.method.HandlerMethod existingHandlerMethod = (registration != null ? registration.getHandlerMethod() : null);
//            if (existingHandlerMethod != null && !existingHandlerMethod.equals(handlerMethod)) {
//                throw new IllegalStateException(
//                        "Ambiguous mapping. Cannot map '" + handlerMethod.getBean() + "' method \n" +
//                                handlerMethod + "\nto " + mapping + ": There is already '" +
//                                existingHandlerMethod.getBean() + "' bean method\n" + existingHandlerMethod + " mapped.");
//            }
//        }
//
//        private void addMappingName(String name, org.springframework.web.method.HandlerMethod handlerMethod) {
//            List<org.springframework.web.method.HandlerMethod> oldList = this.nameLookup.get(name);
//            if (oldList == null) {
//                oldList = Collections.emptyList();
//            }
//
//            for (org.springframework.web.method.HandlerMethod current : oldList) {
//                if (handlerMethod.equals(current)) {
//                    return;
//                }
//            }
//
//            List<org.springframework.web.method.HandlerMethod> newList = new ArrayList<>(oldList.size() + 1);
//            newList.addAll(oldList);
//            newList.add(handlerMethod);
//            this.nameLookup.put(name, newList);
//        }
//
//        public void unregister(T mapping) {
//            this.readWriteLock.writeLock().lock();
//            try {
//                AbstractHandlerMethodMapping.MappingRegistration<T> registration = this.registry.remove(mapping);
//                if (registration == null) {
//                    return;
//                }
//
//                for (String path : registration.getDirectPaths()) {
//                    List<T> mappings = this.pathLookup.get(path);
//                    if (mappings != null) {
//                        mappings.remove(registration.getMapping());
//                        if (mappings.isEmpty()) {
//                            this.pathLookup.remove(path);
//                        }
//                    }
//                }
//
//                removeMappingName(registration);
//
//                this.corsLookup.remove(registration.getHandlerMethod());
//            }
//            finally {
//                this.readWriteLock.writeLock().unlock();
//            }
//        }
//
//        private void removeMappingName(AbstractHandlerMethodMapping.MappingRegistration<T> definition) {
//            String name = definition.getMappingName();
//            if (name == null) {
//                return;
//            }
//            org.springframework.web.method.HandlerMethod handlerMethod = definition.getHandlerMethod();
//            List<org.springframework.web.method.HandlerMethod> oldList = this.nameLookup.get(name);
//            if (oldList == null) {
//                return;
//            }
//            if (oldList.size() <= 1) {
//                this.nameLookup.remove(name);
//                return;
//            }
//            List<org.springframework.web.method.HandlerMethod> newList = new ArrayList<>(oldList.size() - 1);
//            for (HandlerMethod current : oldList) {
//                if (!current.equals(handlerMethod)) {
//                    newList.add(current);
//                }
//            }
//            this.nameLookup.put(name, newList);
//        }
//    }
}
