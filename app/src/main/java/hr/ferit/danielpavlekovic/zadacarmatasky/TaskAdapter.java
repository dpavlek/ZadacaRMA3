package hr.ferit.danielpavlekovic.zadacarmatasky;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by danielpavlekovic on 11/04/2017.
 */

class TaskAdapter extends BaseAdapter{

    private ArrayList<Task> mTasks;

    private String[] priorities;
    private Resources res;

    public TaskAdapter(ArrayList<Task> tasks, Context context){
        res = context.getResources();
        priorities = res.getStringArray(R.array.Priority);
        mTasks = tasks;
    }


    @Override
    public int getCount() {
        return this.mTasks.size();
    }

    @Override
    public Object getItem(int position) {
        return this.mTasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder taskViewHolder;

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.task_element, parent, false);
            taskViewHolder = new ViewHolder(convertView);
            convertView.setTag(taskViewHolder);
        }
        else{
            taskViewHolder = (ViewHolder) convertView.getTag();
        }
        Task task = this.mTasks.get(position);
        taskViewHolder.tvTaskName.setText(task.getName());
        taskViewHolder.tvTaskPriority.setText(task.getPriority());
        taskViewHolder.tvTaskCategory.setText(task.getCategory());
        taskViewHolder.tvTaskDueDate.setText((CharSequence) task.getDueDate());
        return convertView;
    }
}

class ViewHolder{
    public TextView tvTaskName, tvTaskCategory, tvTaskPriority, tvTaskDueDate;
    ImageView ivPriority;

    public ViewHolder(View taskView){
        tvTaskName = (TextView) taskView.findViewById(R.id.tvTaskName);
        tvTaskCategory = (TextView) taskView.findViewById(R.id.tvTaskCategory);
        tvTaskPriority = (TextView) taskView.findViewById(R.id.tvTaskPriority);
        tvTaskDueDate = (TextView) taskView.findViewById(R.id.tvTaskDateDue);
        ivPriority = (ImageView) taskView.findViewById(R.id.ivTaskPriority);
    }
}

