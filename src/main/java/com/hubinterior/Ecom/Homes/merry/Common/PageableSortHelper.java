package com.hubinterior.Ecom.Homes.merry.Common;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;

import java.util.Map;

public final class PageableSortHelper {

    private PageableSortHelper() {
    }

    public static Pageable of(int page, int size, String sort, Map<String, String> allowedFields, String defaultField) {
        return PageRequest.of(page, size, parseSort(sort, allowedFields, defaultField));
    }

    private static Sort parseSort(String sort, Map<String, String> allowedFields, String defaultField) {
        if (sort == null || sort.isBlank()) {
            String defaultColumn = allowedFields.get(defaultField);
            return JpaSort.unsafe(Sort.Direction.ASC, defaultColumn);
        }

        String[] parts = sort.split(",", 2);
        String requestedField = parts[0].trim();
        Sort.Direction direction = parts.length > 1 && "desc".equalsIgnoreCase(parts[1].trim())
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        String column = allowedFields.get(requestedField);
        if (column == null) {
            throw new IllegalArgumentException(
                    "Invalid sort property '" + requestedField + "'. Allowed: " + allowedFields.keySet());
        }

        return JpaSort.unsafe(direction, column);
    }
}
