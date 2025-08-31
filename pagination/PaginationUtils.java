package risrchanish.product.recommend.pagination;

import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PaginationUtils {
	
	private static int sanitizeLimit(int limit) {
	    return limit <= 0 ? 10 : limit;
	}

	private static int sanitizeOffset(int offset) {
	    return Math.max(offset, 0);
	}

	public static Pageable buildPageable(int offset, int limit, Sort sort)
	{
		int sanitizeLimit = sanitizeLimit(limit);
		int sanitizeOffset = sanitizeOffset(offset);
		int page = sanitizeOffset/sanitizeLimit;
		return PageRequest.of(page, sanitizeLimit, sort);
	}
	
	public static Pageable buildPageable(int offset, int limit)
	{
		return buildPageable(offset,limit,Sort.unsorted());
	}
	
	public static <T> List<T> fetchByQuery(Function<Pageable, Page<T>> query, int offset, int limit, Sort sort)
	{
		Pageable pageable = buildPageable(offset, limit, sort);
		return query.apply(pageable).getContent();
	}
	
	public static <T> List<T> fetchByQuery(Function<Pageable,Page<T>> query, int offset, int limit)
	{
		return fetchByQuery(query,offset,limit,Sort.unsorted());
	}
}
