package cn.icodening.router.core;

/**
 * 唯一标识
 *
 * @author icodening
 * @date 2021.04.18
 */
public interface Unique {

    /**
     * 唯一ID
     *
     * @return 唯一标识
     */
    String getId();

    /**
     * 设置一个唯一标识
     *
     * @param id id
     */
    void setId(String id);
}
