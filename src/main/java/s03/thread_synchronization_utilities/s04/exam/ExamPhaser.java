package s03.thread_synchronization_utilities.s04.exam;

import java.util.concurrent.Phaser;

class ExamPhaser extends Phaser {

    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        System.out.println("Phaser: phase " + phase);
        return switch(phase) {
            case 0 -> studentsArrived();
            case 1 -> finishFirstTask();
            case 2 -> finishSecondTask();
            case 3 -> finishExam();
            default -> true;
        };
    }

    private boolean studentsArrived() {
        System.out.printf("Phaser: All students have arrived: %d\n\n", getRegisteredParties());
        return false;
    }

    private boolean finishFirstTask() {
        System.out.println("Phaser: All students finished the first task\n");
        return false;
    }

    private boolean finishSecondTask() {
        System.out.println("Phaser: All students finished the second task\n");
        return false;
    }

    private boolean finishExam() {
        System.out.println("Phaser: All students finished the exam");
        return true;
    }

}
