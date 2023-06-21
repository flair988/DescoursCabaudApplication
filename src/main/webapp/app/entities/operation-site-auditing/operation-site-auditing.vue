<template>
  <div>
    <h2 id="page-heading" data-cy="OperationSiteAuditingHeading">
      <span v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.home.title')" id="operation-site-auditing-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'OperationSiteAuditingCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-operation-site-auditing"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && operationSiteAuditings && operationSiteAuditings.length === 0">
      <span v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="operationSiteAuditings && operationSiteAuditings.length > 0">
      <table class="table table-striped" aria-describedby="operationSiteAuditings">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.itemName')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.cateGory')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.supplier')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.operationSite')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.linkSupplierFactory')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.typeOfSite')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.auditingTool')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.auditingDate')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.csrResult')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.qualityProductionResult')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.businessLiabilityResult')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.comments')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.issueDate')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.dueDate')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.closedDate')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.closed')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.status')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.capComments')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="operationSiteAuditing in operationSiteAuditings" :key="operationSiteAuditing.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'OperationSiteAuditingView', params: { operationSiteAuditingId: operationSiteAuditing.id } }">{{
                operationSiteAuditing.id
              }}</router-link>
            </td>
            <td>{{ operationSiteAuditing.itemName }}</td>
            <td>{{ operationSiteAuditing.cateGory }}</td>
            <td>{{ operationSiteAuditing.supplier }}</td>
            <td>{{ operationSiteAuditing.operationSite }}</td>
            <td>{{ operationSiteAuditing.linkSupplierFactory }}</td>
            <td>{{ operationSiteAuditing.typeOfSite }}</td>
            <td>{{ operationSiteAuditing.auditingTool }}</td>
            <td>{{ operationSiteAuditing.auditingDate }}</td>
            <td>{{ operationSiteAuditing.csrResult }}</td>
            <td>{{ operationSiteAuditing.qualityProductionResult }}</td>
            <td>{{ operationSiteAuditing.businessLiabilityResult }}</td>
            <td>{{ operationSiteAuditing.comments }}</td>
            <td>{{ operationSiteAuditing.issueDate }}</td>
            <td>{{ operationSiteAuditing.dueDate }}</td>
            <td>{{ operationSiteAuditing.closedDate }}</td>
            <td>{{ operationSiteAuditing.closed }}</td>
            <td>{{ operationSiteAuditing.status }}</td>
            <td>{{ operationSiteAuditing.capComments }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'OperationSiteAuditingView', params: { operationSiteAuditingId: operationSiteAuditing.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'OperationSiteAuditingEdit', params: { operationSiteAuditingId: operationSiteAuditing.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(operationSiteAuditing)"
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
          id="descoursCabaudApplicationApp.operationSiteAuditing.delete.question"
          data-cy="operationSiteAuditingDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-operationSiteAuditing-heading"
          v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-operationSiteAuditing"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeOperationSiteAuditing()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./operation-site-auditing.component.ts"></script>
