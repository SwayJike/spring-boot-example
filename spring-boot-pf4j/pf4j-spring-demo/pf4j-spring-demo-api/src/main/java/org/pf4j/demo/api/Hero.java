package org.pf4j.demo.api;

import org.pf4j.ExtensionPoint;

/**
 * 英雄接口
 *
 * @author JourWon
 * @date 2021/9/13
 */
public interface Hero extends ExtensionPoint {

    /**
     * 吃东西
     */
    void eat();

    /**
     * 说话
     */
    void say();

    /**
     * 走路
     */
    void walk();

    /**
     * 跑步
     */
    void run();

    /**
     * 攻击
     */
    void attack();

    /**
     * 防御
     */
    void defense();

}
