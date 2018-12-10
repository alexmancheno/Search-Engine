import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import style from './style.css';

export default class Header extends Component {
    render() {
        return (
            <div className={style.header}>
                <div className="page-header">
                    <Link to='/' style={{textDecoration: 'none', color: 'black'}} ><h1 className="display-4">Phat Search&trade;</h1></Link>
                </div>
            </div>
        )
    }
}