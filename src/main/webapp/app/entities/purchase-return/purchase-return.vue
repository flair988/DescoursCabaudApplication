<template>
  <div>
    <h2 id="page-heading" data-cy="PurchaseReturnHeading">
      <span v-text="t$('descoursCabaudApplicationApp.purchaseReturn.home.title')" id="purchase-return-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('descoursCabaudApplicationApp.purchaseReturn.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'PurchaseReturnCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-purchase-return"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('descoursCabaudApplicationApp.purchaseReturn.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && purchaseReturns && purchaseReturns.length === 0">
      <span v-text="t$('descoursCabaudApplicationApp.purchaseReturn.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="purchaseReturns && purchaseReturns.length > 0">
      <table class="table table-striped" aria-describedby="purchaseReturns">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.purchaseReturn.itemName')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.purchaseReturn.kingdeeId')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.purchaseReturn.docNumber')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.purchaseReturn.supplier')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.purchaseReturn.supplierEmail')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.purchaseReturn.materialReturnDate')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.purchaseReturn.reasonForReturn')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.purchaseReturn.purchaseDept')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.purchaseReturn.remarks')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.purchaseReturn.docStatus')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.purchaseReturn.itemCode')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.purchaseReturn.supplierCode')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="purchaseReturn in purchaseReturns" :key="purchaseReturn.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'PurchaseReturnView', params: { purchaseReturnId: purchaseReturn.id } }">{{
                purchaseReturn.id
              }}</router-link>
            </td>
            <td>{{ purchaseReturn.itemName }}</td>
            <td>{{ purchaseReturn.kingdeeId }}</td>
            <td>{{ purchaseReturn.docNumber }}</td>
            <td>{{ purchaseReturn.supplier }}</td>
            <td>{{ purchaseReturn.supplierEmail }}</td>
            <td>{{ purchaseReturn.materialReturnDate }}</td>
            <td>{{ purchaseReturn.reasonForReturn }}</td>
            <td>{{ purchaseReturn.purchaseDept }}</td>
            <td>{{ purchaseReturn.remarks }}</td>
            <td>{{ purchaseReturn.docStatus }}</td>
            <td>{{ purchaseReturn.itemCode }}</td>
            <td>{{ purchaseReturn.supplierCode }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'PurchaseReturnView', params: { purchaseReturnId: purchaseReturn.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'PurchaseReturnEdit', params: { purchaseReturnId: purchaseReturn.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(purchaseReturn)"
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
          id="descoursCabaudApplicationApp.purchaseReturn.delete.question"
          data-cy="purchaseReturnDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-purchaseReturn-heading"
          v-text="t$('descoursCabaudApplicationApp.purchaseReturn.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-purchaseReturn"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removePurchaseReturn()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./purchase-return.component.ts"></script>
