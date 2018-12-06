import React, { Component } from 'react';
import style from './style.css';

export default class Header extends Component {
    render() {
        return (
            <div className={style.header}>
                <div className="page-header">
                    <h1 className="display-4">Phat Search&trade;</h1>
                </div>
            </div>
        )
    }
}