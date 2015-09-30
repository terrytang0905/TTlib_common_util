package com.newroad.util.cosure;

/**
 * @info  : 可合并的
 * @author: tangzj
 * @data  : 2013-6-9
 * @since : 1.5
 */
public interface Mergeable<Q, T> {
	
	/**
	 *@param T  合并目标对象
	 *@return T 合并结果
	 */
	 Q merger(T v1, T v2);
}
