<template>
  <div>
    <h2 id="page-heading" data-cy="ProcessLogHeading">
      <span v-text="t$('descoursCabaudApplicationApp.processLog.home.title')" id="process-log-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('descoursCabaudApplicationApp.processLog.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ProcessLogCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-process-log"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('descoursCabaudApplicationApp.processLog.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && processLogs && processLogs.length === 0">
      <span v-text="t$('descoursCabaudApplicationApp.processLog.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="processLogs && processLogs.length > 0">
      <table class="table table-striped" aria-describedby="processLogs">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.processLog.type')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.processLog.request')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.processLog.response')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.processLog.status')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.processLog.reason')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.processLog.processStartTime')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.processLog.processEndTime')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="processLog in processLogs" :key="processLog.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ProcessLogView', params: { processLogId: processLog.id } }">{{ processLog.id }}</router-link>
            </td>
            <td>{{ processLog.type }}</td>
            <td>{{ processLog.request }}</td>
            <td>{{ processLog.response }}</td>
            <td>{{ processLog.status }}</td>
            <td>{{ processLog.reason }}</td>
            <td>{{ processLog.processStartTime }}</td>
            <td>{{ processLog.processEndTime }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ProcessLogView', params: { processLogId: processLog.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'ProcessLogEdit', params: { processLogId: processLog.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(processLog)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="t$('entity.action.delete')"></span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <template #modal-title>
        <span
          id="descoursCabaudApplicationApp.processLog.delete.question"
          data-cy="processLogDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-processLog-heading" v-text="t$('descoursCabaudApplicationApp.processLog.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-processLog"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeProcessLog()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./process-log.component.ts"></script>
