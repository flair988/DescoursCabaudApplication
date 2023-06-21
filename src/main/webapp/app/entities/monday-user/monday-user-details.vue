<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="mondayUser">
        <h2 class="jh-entity-heading" data-cy="mondayUserDetailsHeading">
          <span v-text="t$('descoursCabaudApplicationApp.mondayUser.detail.title')"></span> {{ mondayUser.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.mondayUser.mondayId')"></span>
          </dt>
          <dd>
            <span>{{ mondayUser.mondayId }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.mondayUser.name')"></span>
          </dt>
          <dd>
            <span>{{ mondayUser.name }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.mondayUser.email')"></span>
          </dt>
          <dd>
            <span>{{ mondayUser.email }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.mondayUser.url')"></span>
          </dt>
          <dd>
            <span>{{ mondayUser.url }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="mondayUser.id"
          :to="{ name: 'MondayUserEdit', params: { mondayUserId: mondayUser.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./monday-user-details.component.ts"></script>
