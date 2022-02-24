package tasks;

import exceptions.DukeException;
import times.DukeTime;

import java.util.HashMap;
import java.util.Map;

public class EventTask extends Task {
    private DukeTime startTime;
    private DukeTime endTime;
    private static final String START_TIME_FIELD = "startTime";
    private static final String END_TIME_FIELD = "endTime";

    public EventTask(String taskDescription, String taskType) {
        super(taskDescription, taskType);
    }


    /**
     * Initializes task with compressed object
     * @param compressedObject Compressed object that contains all model information for initialize that task
     */
    public EventTask(HashMap<String, Object> compressedObject) {
        super(compressedObject);
        startTime = (DukeTime) compressedObject.get(START_TIME_FIELD);
        endTime = (DukeTime) compressedObject.get(END_TIME_FIELD);
    }

    /**
     * Initializes task with task description, taskType, dateTime.
     *
     * @param taskDescription The description of the task
     * @param taskType The type of the task
     * @param dateTime The time of the task
     */
    public EventTask(String taskDescription, String taskType, String dateTime) throws DukeException {
        super(taskDescription, taskType);
        try {
            if (dateTime.contains("/to")) {
                this.startTime = new DukeTime(dateTime.split("/to")[0].trim());
                this.endTime = new DukeTime(dateTime.split("/to")[1].trim());
            }
            else {
                this.startTime = new DukeTime(dateTime);
                this.endTime = null;
            }

        } catch (DukeException e) {
            throw e;
        }

    }


    /**
     * Sets dateTime.
     *
     * @param dateTime The time of the task
     */
    public void setDateTime(String dateTime) throws DukeException {
        try {
            this.startTime = new DukeTime(dateTime);
        } catch (DukeException e) {
            throw e;
        }

    }


    /**
     * Gets the report of the event task.
     *
     * @return The report of the event task
     */
    @Override
    public String getReport() {
        if (endTime == null) {
            return String.format("[%s][%s] %s (at: %s)", taskType, markedSign(), taskDescription, startTime.toString());
        }
        else {
            return String.format("[%s][%s] %s (from: %s to %s)", taskType, markedSign(), taskDescription, startTime.toString(), endTime.toString());
        }
    }

    /**
     * Compresses the task objects into a map objects
     * @return A compressed object that can initialize the model task again
     */
    @Override
    public HashMap<String, Object> compress() {
        HashMap<String, Object> compressedObject = super.compress();
        compressedObject.put(START_TIME_FIELD, startTime);
        compressedObject.put(END_TIME_FIELD, endTime);
        return compressedObject;
    }

}
