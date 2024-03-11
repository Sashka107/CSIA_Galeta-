package com.csia_galeta.ser;

/**
 * Class CompetitionStates
 * Этот утилитарный класс констант содержит константы состояний
 * для соревнований
 *
 * @author Alexander G.
 */
public final class CompetitionStates {

    // константа для созданного, но не начатого соревнования
    public static final String PLANNED = "Planned";

    // константа для начатого этапа квалификации в соревновании
    public static final String QUALIFICATION_IN_PROGRESS = "Qualification In Progress";

    // константа для начатого этапа парных заездов в соревновании
    public static final String RUN_IN_PAIRS_IN_PROGRESS = "Pairs run in Progress";

    // константа для финального этапа парных заездов в соревновании
    public static final String FINAL_ROUND = "Competition Final";

    // константа для завершенного соревнования
    public static final String COMPETITION_IS_FINISHED = "Competition is Finished";
}