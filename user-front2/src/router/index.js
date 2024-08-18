import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/login/Login.vue'
import Index from '@/components/index/index.vue'
import UserList from '@/components/user/UserList'
import foodlist from '@/components/food/foodlist.vue'
import billiards from '@/components/billiards/billiards.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
	{
		path:'/sys',
		name:'Index',
		component:Index,
		children:[{
				//子路由的path路径上不能加‘/’.
				path:'userList',
				name:'UserList',
				component:UserList
			},
      {
        path:'foodlist',
        name:'foodlist',
        component:foodlist
      },
      {
        path:'billiards',
        name:'billiards',
        component:billiards
      }
		]
	}
  ]
})
