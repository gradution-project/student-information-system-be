package com.graduationproject.studentinformationsystem.university.featuretoggle.repository.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisDatabaseException;
import com.graduationproject.studentinformationsystem.common.util.log.SisErrorLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisInfoLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisWarnLogMessageUtil;
import com.graduationproject.studentinformationsystem.university.featuretoggle.model.entity.FeatureToggleEntity;
import com.graduationproject.studentinformationsystem.university.featuretoggle.model.enums.FeatureToggleName;
import com.graduationproject.studentinformationsystem.university.featuretoggle.repository.FeatureToggleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

import static com.graduationproject.studentinformationsystem.university.featuretoggle.model.mapping.FeatureToggleMapping.*;
import static com.graduationproject.studentinformationsystem.university.featuretoggle.repository.impl.scripts.FeatureToggleSqlScripts.*;

@Repository
@RequiredArgsConstructor
public class FeatureToggleRepositoryImpl implements FeatureToggleRepository {

    private static final String FEATURE_TOGGLE = "Feature Toggle";
    private final SisInfoLogMessageUtil info = SisInfoLogMessageUtil.builder().apiName(FEATURE_TOGGLE).build();
    private final SisWarnLogMessageUtil warn = SisWarnLogMessageUtil.builder().apiName(FEATURE_TOGGLE).build();
    private final SisErrorLogMessageUtil error = SisErrorLogMessageUtil.builder().apiName(FEATURE_TOGGLE).build();

    private final Sql2o sql2o;

    public List<FeatureToggleEntity> getAllFeatureToggles() {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_ALL_FEATURE_TOGGLES)) {

            final List<FeatureToggleEntity> featureToggleEntities = query
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetch(FeatureToggleEntity.class);

            info.foundAll();
            return featureToggleEntities;
        } catch (Exception exception) {
            error.errorWhenGettingAll();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public FeatureToggleEntity getFeatureToggleByName(final FeatureToggleName name) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_FEATURE_TOGGLE_BY_NAME)) {

            final FeatureToggleEntity featureToggleEntity = query.addParameter(NAME.getModelName(), name)
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetchFirst(FeatureToggleEntity.class);

            info.foundByName(name.toString());
            return featureToggleEntity;
        } catch (Exception exception) {
            error.errorWhenGettingByName(name.toString());
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void enableFeatureToggle(final FeatureToggleEntity featureToggleEntity) {
        updateFeatureToggleValue(featureToggleEntity, ENABLE_FEATURE_TOGGLE);
    }

    @Override
    public void disableFeatureToggle(final FeatureToggleEntity featureToggleEntity) {
        updateFeatureToggleValue(featureToggleEntity, DISABLE_FEATURE_TOGGLE);
    }

    @Override
    public boolean isFeatureToggleExist(final FeatureToggleName name) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(IS_FEATURE_TOGGLE_EXIST_BY_NAME)) {

            final boolean isFeatureToggleExist = query
                    .addParameter(NAME.getModelName(), name)
                    .executeScalar(Boolean.class);

            if (isFeatureToggleExist) {
                info.foundByName(name.toString());
                return true;
            } else {
                warn.notFoundByName(name.toString());
                return false;
            }
        } catch (Exception exception) {
            error.errorWhenGettingByName(name.toString());
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public boolean isFeatureToggleEnabled(final FeatureToggleName name) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(IS_FEATURE_TOGGLE_ENABLED)) {

            final boolean isFeatureToggleEnabled = query
                    .addParameter(NAME.getModelName(), name)
                    .executeScalar(Boolean.class);

            if (isFeatureToggleEnabled) {
                info.foundByName(name.toString());
                return true;
            } else {
                warn.notFoundByName(name.toString());
                return false;
            }
        } catch (Exception exception) {
            error.errorWhenGettingByName(name.toString());
            throw new SisDatabaseException(exception);
        }
    }

    private void updateFeatureToggleValue(final FeatureToggleEntity featureToggleEntity, final String sql) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(sql)) {

            query.addParameter(NAME.getModelName(), featureToggleEntity.getName())
                    .addParameter(MODIFIED_USER_ID.getModelName(), featureToggleEntity.getModifiedUserId())
                    .addParameter(MODIFIED_DATE.getModelName(), featureToggleEntity.getModifiedDate())
                    .executeUpdate();

            info.updated();
        } catch (Exception exception) {
            error.errorWhenUpdating();
            throw new SisDatabaseException(exception);
        }
    }
}
