import React from 'react'
import './styles.css'

const Landing = () => {
    return(      
            <div className="container">

                <div className="landingLeft">
                    <h1>Bem-vindo ao Postgram</h1>
                </div>

                <div className="landingRight">
                    <ul>
                        <li><a href="/login">login</a></li>
                        <li><a href="/register">register</a></li>
                    </ul>
                </div>

            </div>         
    );
}

export default Landing