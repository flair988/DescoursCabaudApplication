<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="group">
        <h2 class="jh-entity-heading" data-cy="groupDetailsHeading">
          <span v-text="t$('descoursCabaudApplicationApp.group.detail.title')"></span> {{ group.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.group.groupId')"></span>
          </dt>
          <dd>
            <span>{{ group.groupId }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.group.groupNumber')"></span>
          </dt>
          <dd>
            <span>{{ group.groupNumber }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.group.parentId')"></span>
          </dt>
          <dd>
            <span>{{ group.parentId }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.group.groupName')"></span>
          </dt>
          <dd>
            <span>{{ group.groupName }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.group.description')"></span>
          </dt>
          <dd>
            <span>{{ group.description }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link v-if="group.id" :to="{ name: 'GroupEdit', params: { groupId: group.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./group-details.component.ts"></script>
