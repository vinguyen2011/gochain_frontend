package com.gochain.gochainandroid.vo;

import java.util.List;

/**
 * Created by Brad on 11/02/2017.
 */

public class ProjectFlatVoContainer {
    private List<ProjectFlatVo> projectFlatVos;

    public ProjectFlatVoContainer() {
    }

    public ProjectFlatVoContainer(List<ProjectFlatVo> projectFlatVos) {
        this.projectFlatVos = projectFlatVos;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (projectFlatVos != null) {
            for (ProjectFlatVo flatVo: projectFlatVos) {
                stringBuilder.append(flatVo.toString());
            }
        }
        return "ProjectFlatVoContainer{" +
                stringBuilder.toString() +
                '}';
    }

    public List<ProjectFlatVo> getProjectFlatVos() {
        return projectFlatVos;
    }

    public void setProjectFlatVos(List<ProjectFlatVo> projectFlatVos) {
        this.projectFlatVos = projectFlatVos;
    }
}
