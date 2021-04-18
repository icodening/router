package cn.icodening.router.core;

import static java.lang.Integer.compare;

/**
 * 优先级，用于排序
 * 数值越小越靠前
 *
 * @author icodening
 * @date 2021.04.17
 */
public interface Priority extends Comparable<Priority> {

    /**
     * 最大优先级
     */
    int MAX_PRIORITY = Integer.MIN_VALUE;

    /**
     * 最小优先级
     */
    int MIN_PRIORITY = Integer.MAX_VALUE;

    /**
     * 正常优先级
     */
    int NORMAL_PRIORITY = 0;


    /**
     * 获取优先级
     *
     * @return 优先级数值
     */
    default int getPriority() {
        return NORMAL_PRIORITY;
    }

    @Override
    default int compareTo(Priority that) {
        return compare(this.getPriority(), that.getPriority());
    }

}
