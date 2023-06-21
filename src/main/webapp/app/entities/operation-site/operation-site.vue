<template>
  <div>
    <h2 id="page-heading" data-cy="OperationSiteHeading">
      <span v-text="t$('descoursCabaudApplicationApp.operationSite.home.title')" id="operation-site-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('descoursCabaudApplicationApp.operationSite.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'OperationSiteCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-operation-site"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('descoursCabaudApplicationApp.operationSite.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && operationSites && operationSites.length === 0">
      <span v-text="t$('descoursCabaudApplicationApp.operationSite.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="operationSites && operationSites.length > 0">
      <table class="table table-striped" aria-describedby="operationSites">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.operationSite.itemName')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.operationSite.linkSupplierFactory')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.operationSite.typeOfSite')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.operationSite.contact')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.operationSite.siteAddress')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.operationSite.cateGory')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.operationSite.country')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.operationSite.kingdeeId')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.operationSite.businessLicense')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.operationSite.sasDate')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.operationSite.iso900ValidUtil')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.operationSite.iso14001ValidUtil')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.operationSite.attributeLor')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.operationSite.siteQualification')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.operationSite.qualificationScore')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.operationSite.pqvScore')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.operationSite.pqvDate')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.operationSite.pqvDecision')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.operationSite.technicalAuditDate')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.operationSite.technicalAuditScore')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.operationSite.thirdRdPartyDate')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.operationSite.thirdRdPartyScore')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.operationSite.bopeDate')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.operationSite.bopeScore')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.operationSite.capRequired')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="operationSite in operationSites" :key="operationSite.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'OperationSiteView', params: { operationSiteId: operationSite.id } }">{{
                operationSite.id
              }}</router-link>
            </td>
            <td>{{ operationSite.itemName }}</td>
            <td>{{ operationSite.linkSupplierFactory }}</td>
            <td>{{ operationSite.typeOfSite }}</td>
            <td>{{ operationSite.contact }}</td>
            <td>{{ operationSite.siteAddress }}</td>
            <td>{{ operationSite.cateGory }}</td>
            <td>{{ operationSite.country }}</td>
            <td>{{ operationSite.kingdeeId }}</td>
            <td>{{ operationSite.businessLicense }}</td>
            <td>{{ operationSite.sasDate }}</td>
            <td>{{ operationSite.iso900ValidUtil }}</td>
            <td>{{ operationSite.iso14001ValidUtil }}</td>
            <td>{{ operationSite.attributeLor }}</td>
            <td>{{ operationSite.siteQualification }}</td>
            <td>{{ operationSite.qualificationScore }}</td>
            <td>{{ operationSite.pqvScore }}</td>
            <td>{{ operationSite.pqvDate }}</td>
            <td>{{ operationSite.pqvDecision }}</td>
            <td>{{ operationSite.technicalAuditDate }}</td>
            <td>{{ operationSite.technicalAuditScore }}</td>
            <td>{{ operationSite.thirdRdPartyDate }}</td>
            <td>{{ operationSite.thirdRdPartyScore }}</td>
            <td>{{ operationSite.bopeDate }}</td>
            <td>{{ operationSite.bopeScore }}</td>
            <td>{{ operationSite.capRequired }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'OperationSiteView', params: { operationSiteId: operationSite.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'OperationSiteEdit', params: { operationSiteId: operationSite.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(operationSite)"
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
          id="descoursCabaudApplicationApp.operationSite.delete.question"
          data-cy="operationSiteDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-operationSite-heading"
          v-text="t$('descoursCabaudApplicationApp.operationSite.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-operationSite"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeOperationSite()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./operation-site.component.ts"></script>
