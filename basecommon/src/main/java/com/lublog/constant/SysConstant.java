package com.lublog.constant;

public interface SysConstant {
    String CURRENT_USER = "currentUser";

    String ONLINE_VISITOR = "onlineVisitor";

    String PROFILE_SPACE = "PROFILE SPACE";

    String NODE_OPEN_STATUS = "open";

    String NODE_CLOSED_STATUS = "closed";

    // 文章点击的频率
    String HITS_FREQUENCY = "hits:frequency";

    /**
     * 同一篇文章在5分钟内无论点击多少次只算一次阅读
     */
    long HITS_LIMIT_TIME = 300;

    String VISIT_COUNT = "visit_count";

    /**
     * 访问次数记录，30分钟
     */
    public static Integer VISIT_COUNT_TIME = 1800;

    // 文章同一ip点赞标志
    String LIKE_FLAG = "like_flag";

    int NUM_1 = 1;

    int NUM_0 = 0;


}
