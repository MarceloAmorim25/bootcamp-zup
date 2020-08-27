import React from "react";
import { Route, BrowserRouter } from "react-router-dom";
import Login from './pages/login'
import Landing from './pages/landing'
import Register from './pages/register'
import Postlist from './pages/postlist'
import Profile from './pages/profile'

const Routes = () => (
    
      <BrowserRouter>
          <Route path="/" component={Landing} exact/>
          <Route path="/login" component={Login} />
          <Route path="/register" component={Register} />
          <Route path="/postlist" component={Postlist} />
          <Route path="/profile" component={Profile} />
      </BrowserRouter>       
  );
  
  export default Routes;