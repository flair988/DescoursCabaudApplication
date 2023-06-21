<template>
  <div>
    <h2 id="page-heading" data-cy="SupplierHeading">
      <span v-text="t$('descoursCabaudApplicationApp.supplier.home.title')" id="supplier-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('descoursCabaudApplicationApp.supplier.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'SupplierCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-supplier"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('descoursCabaudApplicationApp.supplier.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && suppliers && suppliers.length === 0">
      <span v-text="t$('descoursCabaudApplicationApp.supplier.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="suppliers && suppliers.length > 0">
      <table class="table table-striped" aria-describedby="suppliers">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.supplier.client')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.supplier.category')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.supplier.subCategory')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.supplier.capStatus')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.supplier.supplierStatus')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.supplier.qualificationScore')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.supplier.bopeScore')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.supplier.internalSupplierId')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.supplier.contact')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.supplier.contactPhoneNumber')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.supplier.contactEmailAddress')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.supplier.operationSite')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.supplier.address')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.supplier.website')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.supplier.relationStartingYear')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.supplier.businessLicense')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.supplier.rexOriginStatus')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.supplier.createDate')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.supplier.updateDate')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.supplier.item')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.supplier.subItems')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.supplier.date')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.supplier.kingdeeId')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.supplier.region')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.supplier.supplierType')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.supplier.remark')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.supplier.frenchName')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="supplier in suppliers" :key="supplier.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'SupplierView', params: { supplierId: supplier.id } }">{{ supplier.id }}</router-link>
            </td>
            <td>{{ supplier.client }}</td>
            <td>{{ supplier.category }}</td>
            <td>{{ supplier.subCategory }}</td>
            <td>{{ supplier.capStatus }}</td>
            <td>{{ supplier.supplierStatus }}</td>
            <td>{{ supplier.qualificationScore }}</td>
            <td>{{ supplier.bopeScore }}</td>
            <td>{{ supplier.internalSupplierId }}</td>
            <td>{{ supplier.contact }}</td>
            <td>{{ supplier.contactPhoneNumber }}</td>
            <td>{{ supplier.contactEmailAddress }}</td>
            <td>{{ supplier.operationSite }}</td>
            <td>{{ supplier.address }}</td>
            <td>{{ supplier.website }}</td>
            <td>{{ supplier.relationStartingYear }}</td>
            <td>{{ supplier.businessLicense }}</td>
            <td>{{ supplier.rexOriginStatus }}</td>
            <td>{{ supplier.createDate }}</td>
            <td>{{ supplier.updateDate }}</td>
            <td>{{ supplier.item }}</td>
            <td>{{ supplier.subItems }}</td>
            <td>{{ supplier.date }}</td>
            <td>{{ supplier.kingdeeId }}</td>
            <td>{{ supplier.region }}</td>
            <td>{{ supplier.supplierType }}</td>
            <td>{{ supplier.remark }}</td>
            <td>{{ supplier.frenchName }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'SupplierView', params: { supplierId: supplier.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'SupplierEdit', params: { supplierId: supplier.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(supplier)"
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
          id="descoursCabaudApplicationApp.supplier.delete.question"
          data-cy="supplierDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-supplier-heading" v-text="t$('descoursCabaudApplicationApp.supplier.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-supplier"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeSupplier()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./supplier.component.ts"></script>
